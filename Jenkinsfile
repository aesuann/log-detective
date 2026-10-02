cd ~/github/log-detective
cat > Jenkinsfile << 'EOF'
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking code out'
            }
        }

        stage('Build Service A') {
            steps {
                echo 'Building Service A...'
                dir('services/service-a/servicea') {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Service B') {
            steps {
                echo 'Building Service B'
                dir('services/service-b/serviceb') {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Testing services'
                // Пока тестов нет, просто заглушка
                // В будущем здесь будут:
                // dir('services/service-a/servicea') { sh './mvnw test' }
                // dir('services/service-b/serviceb') { sh './mvnw test' }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed'
        }
        success {
            echo 'All stages passed'
        }
        failure {
            echo ' Pipeline failed'
        }
    }
}
EOF