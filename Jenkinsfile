pipeline {
    agent any

    tools {
        jdk 'jdk-11'
    }

    stages {
        stage("Clean") {
            steps {
                sh "chmod +x ./gradlew";
                sh "rm -rf ./out/ || true";
                sh "./gradlew licenseFormat clean";
            }
        }
        stage("Build") {
            steps {
                sh "./gradlew build";
            }
        }
        stage("Test") {
            steps {
                sh "./gradlew test";
            }
        }
        stage("Publish") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                  sh "./gradlew publish -DpublishPassword=$PASSWORD -DpublishName=$USERNAME"
                }
            }
        }
        stage("Build ShadowJar") {
            steps {
                sh "./gradlew shadowJar";
            }
            post {
                success {
                    archiveArtifacts artifacts: 'out/unshaded/*.jar', fingerprint: true
                }
            }
        }
    }
}
