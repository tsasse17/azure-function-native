terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "3.0.2"
    }
  }
}

provider "azurerm" {
  features {}
}

resource "azurerm_resource_group" "main" {
  name     = var.AZ_RESOURCE_GROUP
  location = var.AZ_LOCATION
  tags = {
    owner: "gedeom.tsasse@exxeta.com"

  }
}

resource "azurerm_storage_account" "main" {
  name                            = var.AZ_STORAGE_NAME
  resource_group_name             = azurerm_resource_group.main.name
  location                        = azurerm_resource_group.main.location
  account_tier                    = "Standard"
  account_replication_type        = "LRS"
  enable_https_traffic_only       = true
  allow_nested_items_to_be_public = false
}

resource "azurerm_service_plan" "main" {
  name                = "${var.AZ_FUNCTION_NAME_APP}-plan"
  location            = azurerm_resource_group.main.location
  resource_group_name = azurerm_resource_group.main.name

  sku_name = "Y1"
  os_type  = "Linux"
}

resource "azurerm_linux_function_app" "main" {
  name                        = var.AZ_FUNCTION_NAME_APP
  location                    = azurerm_resource_group.main.location
  resource_group_name         = azurerm_resource_group.main.name
  service_plan_id             = azurerm_service_plan.main.id
  storage_account_name        = azurerm_storage_account.main.name
  storage_account_access_key  = azurerm_storage_account.main.primary_access_key
  https_only                  = true
  functions_extension_version = "~4"
  auth_settings {
    enabled = false
  }
  site_config {
    always_on = false
  }
  app_settings = {
    FUNCTIONS_WORKER_RUNTIME  = "powershell"
  }
}


resource "azurerm_application_insights" "example" {
  name                = "${var.AZ_FUNCTION_NAME_APP}-app-insight"
  location            = azurerm_resource_group.main.location
  resource_group_name = azurerm_resource_group.main.name
  application_type    = "java"

}