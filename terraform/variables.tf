variable "AZ_LOCATION" {
  description = "The Azure location where all resources in this example should be created"
  default     = "northeurope"
}

variable "AZ_RESOURCE_GROUP" {
  description = "The resource group"
  default = "rg-native-test"
}

variable "AZ_FUNCTION_NAME_APP" {
  description = "The name of the application running functions"
  default = "azure-native-spring-function"
}

variable "AZ_STORAGE_NAME" {
  description = "The name of the storage account"
  default = "storage122web3"
}
