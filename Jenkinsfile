pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo '[GIT] Cloning log-detective repo'
            }
        }

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
                echo '[TEST] no unit tests configured yet'
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