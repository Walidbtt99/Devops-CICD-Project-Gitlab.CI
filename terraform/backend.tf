terraform {
  backend "local" {
    path = "./terraform.tfstate"
  }
}


#Defines how Terraform saves its state remotely.

