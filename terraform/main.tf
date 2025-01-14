provider "azurerm" {
  features {}
  use_cli = true  # Use Azure CLI for authentication
  subscription_id = var.subscription_id
  tenant_id       = var.tenant_id
}

resource "azurerm_resource_group" "rg" {
  name     = var.resource_group_name
  location = var.location
}

resource "azurerm_kubernetes_cluster" "aks" {
  name                = var.aks_cluster_name
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  dns_prefix          = "k8s"

  # Configure Managed Identity
  identity {
    type = "SystemAssigned"
  }

  default_node_pool {
    name       = "default"
    node_count = var.node_count
    vm_size    = "Standard_DS2_v2"
  }

  # Optional: Define the network plugin for AKS
  #network_profile {
  #  network_plugin = "azure"
  #}
}
