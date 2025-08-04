pipeline {
    agent any

    tools {
        maven 'maven 3.9.3'
    }

    triggers {
        cron('0 12 * * *')  // Runs at 12:00 PM
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/meghadubey23/rest-java-testng.git'
            }
        }

        stage('Build and Run Smoke Tests') {
            steps {
                sh 'mvn clean test -Dgroups=smoke'
            }
            post {
                always {
                    testng '**/target/surefire-reports/*.xml'
                }
            }
        }
    }
}
