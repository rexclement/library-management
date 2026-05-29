pipeline {
    agent any

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                url: 'https://github.com/rexclement/library-management.git'
            }
        }

        stage('Build Maven Project') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t library-app .'
            }
        }

        stage('Stop Old Container') {
            steps {
                sh 'docker stop library-container || true'
                sh 'docker rm library-container || true'
            }
        }

        stage('Run New Container') {
            steps {
                sh '''
                docker run -d \
                  --name library-container \
                  -p 8080:8080 \
                  library-app
                '''
            }
        }

        stage('Verify Running Containers') {
            steps {
                sh 'docker ps'
            }
        }
    }
}