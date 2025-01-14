- Spring Boot Backend CI/CD Pipeline with GitLab CI/CD

This project uses GitLab CI/CD to automate the build, test, code analysis, containerization, vulnerability scanning, and deployment of a Spring Boot backend application. The pipeline is configured to ensure continuous integration and delivery, supporting local deployment with Docker Compose and infrastructure provisioning using Terraform.

- Pipeline Overview
The pipeline is designed with multiple stages to handle:

 + Compilation and testing
 + Static code analysis with SonarQube
 + Application packaging
 + Docker image creation and vulnerability scanning with Trivy
 + Image pushing to a Docker registry
 + Deployment using Docker Compose
 + Optional infrastructure provisioning with Terraform

- Pipeline Stages

 1. The pipeline consists of the following stages:
 2. Compile: Compiles the application with Maven.
 3. Junit/Mockito Tests: Runs unit and integration tests with MySQL as a service.
 4. SonarQube Analysis: Performs static code analysis.
 5. Build the Application: Packages the Spring Boot application.
 6. Build Docker Image: Builds the application Docker image.
 7. Trivy Scan: Scans the Docker image for vulnerabilities.
 8. Push Docker Image: Pushes the Docker image to a registry.
 9. Deploy with Docker Compose: Deploys the application locally.
 10. Terraform Plan (Optional): Prepares infrastructure provisioning.
 11. Approval (Optional): Awaits manual approval for production changes.
 12. Terraform Apply (Optional): Provisions infrastructure changes.

- Prerequisites

 1. Tools and Services:
  + GitLab Runner
  + Docker
  + Docker Compose
  + SonarQube  
  + Trivy
  + Terraform
  + MySQL

 2. Environment Variables:
  + DOCKER_USERNAME: Docker Hub username.
  + DOCKER_PASSWORD: Docker Hub password.
  + SONARQUBE_SERVER_URL: URL for the SonarQube server.
  + SONARQUBE_TOKEN: Authentication token for SonarQube.

- Setup Instructions
 1. Clone the Repository:
  git clone https://github.com/Walidbtt99/Devops-CICD-Project-Gitlab.CI.git
  cd Devops-CICD-Project-Gitlab.CI

 2. Configure GitLab CI/CD:
  + Ensure the .gitlab-ci.yml file is in the root directory.
  + Add the required environment variables in the GitLab CI/CD settings under "Settings > CI/CD > Variables."

 3. Start Required Services:
  + Run a local SonarQube server or ensure access to a remote SonarQube instance in our case we're woking with Ngrok.
  + Configure MySQL service with the appropriate credentials.

 4. Install Dependencies:
  + Ensure Maven, Docker, and Terraform are installed and accessible on the CI/CD runner.

- Detailed Pipeline Stages
 1. Compile
  Compiles the Spring Boot application using Maven.
  Outputs build artifacts to the target/ directory.
 2. Junit/Mockito Tests
  Runs unit tests.
  Ensures a MySQL service is available during the tests.
 3. SonarQube Analysis
  Performs static code analysis using SonarQube.
  Requires SONARQUBE_SERVER_URL and SONARQUBE_TOKEN.
 4. Build the Application
  Packages the Spring Boot application into a .jar file.
 5. Build Docker Image
  Creates a Docker image from the application artifacts.
 6. Trivy Scan
  Scans the built Docker image for vulnerabilities.
 7. Push Docker Image
  Pushes the Docker image to the specified registry.
 8. Deploy with Docker Compose
  Stops any running containers and deploys the application using docker-compose.
 9. Terraform Plan (Optional)
  Prepares the Terraform plan for infrastructure changes.
 10. Approval (Optional)
  Awaits manual approval for applying the Terraform plan.
 11. Terraform Apply (Optional)
  Applies the Terraform plan to provision infrastructure.

- Tools and Services Used
 + Maven: For building and testing the Spring Boot application.
 + SonarQube: For static code analysis.
 + Docker: For containerization.
 + Trivy: For Docker image vulnerability scanning.
 + Terraform: For infrastructure provisioning.

- Troubleshooting
  + Pipeline Failures:

   .Check the logs of the failed stage for details.
   .Verify environment variable configurations.
   .SonarQube Connection Issues:

   .Ensure the SONARQUBE_SERVER_URL is correct and accessible.
   .Verify the SONARQUBE_TOKEN.
  
  + Docker Issues:

   .Check Docker service status on the CI/CD runner.
   .Ensure credentials for Docker Hub are correctly set.
  
  + Database Connection:

   .Ensure MySQL service is running and accessible.
   .Verify the database credentials.
