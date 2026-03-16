pipeline {
    agent any

    environment {
        GIT_REPO_URL    = 'https://github.com/nikhilbhatt-fostering/git-jenkins-practice.git'
        GIT_BRANCH      = 'main'
        TOMCAT_HOST     = '10.88.0.62'        // ← podman container IP
        TOMCAT_PORT     = '8085'
        TOMCAT_USER     = 'admin'
        TOMCAT_PASSWORD = 'admin'
        APP_NAME        = 'crud-app'
        WAR_FILE        = 'target/java-crud-web-1.0.war'
        APP_URL         = "http://${TOMCAT_HOST}:${TOMCAT_PORT}/${APP_NAME}/employees/"
    }

    tools {
        maven 'Maven-3.9'
        jdk   'JDK-11'
    }

    stages {
        stage('Clone Code from Git') {
            steps {
                git branch: "${GIT_BRANCH}", url: "${GIT_REPO_URL}"
                echo "✅ Code cloned"
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean compile -q'
                echo "✅ Build successful"
            }
        }

        stage('Generate WAR File') {
            steps {
                sh 'mvn package -DskipTests -q'
                archiveArtifacts artifacts: "${WAR_FILE}", fingerprint: true
                echo "✅ WAR generated"
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sh """
                    curl -u ${TOMCAT_USER}:${TOMCAT_PASSWORD} \
                         -T ${WAR_FILE} \
                         "http://${TOMCAT_HOST}:${TOMCAT_PORT}/manager/text/deploy?path=/${APP_NAME}&update=true"
                """
                echo "✅ Deployed to Tomcat"
            }
        }

        stage('Verify Application URL') {
            steps {
                sleep(time: 20, unit: 'SECONDS')
                script {
                    def status = sh(
                        script: "curl -s -o /dev/null -w '%{http_code}' ${APP_URL}",
                        returnStdout: true
                    ).trim()
                    echo "HTTP Status: ${status}"
                    if (status != '200') {
                        error("❌ App not reachable! Status: ${status}")
                    }
                    echo "✅ App is live at ${APP_URL}"
                }
            }
        }
    }

    post {
        success { echo "🎉 Pipeline PASSED — App live at ${APP_URL}" }
        failure { echo "❌ Pipeline FAILED — check logs" }
        always  { cleanWs() }
    }
}
