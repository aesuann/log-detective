pipeline {
    agent any

    stages {
        stage('Build Service A') {
            steps {
                echo '[MAVEN] Packaging service-a (JDK 17, skipTests)'
                dir('services/service-a/servicea') {
                    sh './mvnw clean package -DskipTests -q'
                }
            }
        }

        stage('Build Service B') {
            steps {
                echo '[MAVEN] Packaging service-b (JDK 17, skipTests)'
                dir('services/service-b/serviceb') {
                    sh './mvnw clean package -DskipTests -q'
                }
            }
        }

        stage('Test') {
            steps {
                dir('services/service-a/servicea') {
                    sh './mvnw test'
                }
            }
        }

        stage('Docker Build Service A') {
            steps {
                dir('services/service-a/servicea') {
                    sh 'DOCKER_BUILDKIT=0 docker build -t service-a:latest .'
                }
            }
        }

        stage('Docker Build Service B') {
            steps {
                dir('services/service-b/serviceb') {
                    sh 'DOCKER_BUILDKIT=0 docker build -t service-b:latest .'
                }
            }
        }
    }

    post {
        always {
            echo '[PIPELINE] Execution finished'
        }
        success {
            echo '[STATUS] All stages passed'
        }
        failure {
            echo '[STATUS] Pipeline FAILED.'
        }
    }
}