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

        stage('Docker Build Service A') {
            steps {
                dir('services/service-a/servicea') {
                    sh '''
                        eval $(minikube docker-env)
                        DOCKER_BUILDKIT=0 docker build -t service-a:latest .
                    '''
                }
            }
        }

        stage('Docker Build Service B') {
            steps {
                dir('services/service-b/serviceb') {
                    sh '''
                        eval $(minikube docker-env)
                        DOCKER_BUILDKIT=0 docker build -t service-b:latest .
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                    kubectl apply -f k8s/service-a-deployment.yaml
                    kubectl apply -f k8s/service-b-configmap.yaml
                    kubectl apply -f k8s/service-b-deployment.yaml
                '''
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
            echo '[STATUS] Pipeline FAILED'
        }
    }
}