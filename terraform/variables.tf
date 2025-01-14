variable "resource_group_name" {
  default = "DevopsProjectRg"
}

variable "aks_cluster_name" {
  default = "DevopsProjectAKSCluster"
}

variable "location" {
  default = "eastus"
}

variable "node_count" {
  default = 1
}

variable "subscription_id" {
  description = "The Azure Subscription ID"
  type        = string
}

variable "tenant_id" {
  description = "The Azure tenant ID"
  type        = string
}