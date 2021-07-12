pipeline {
    agent {
      label 'maven-jdk11-openj9'
    }

    environment {
        POM_VERSION = readMavenPom().getVersion()
    }

    stages {
        stage("Checkout") {
            steps {
                scmSkip(deleteBuild: true, skipPattern:'.*\\[ci skip\\].*')
                buildName "#${BUILD_NUMBER} ${POM_VERSION}"
            }
        }

        stage('Build') {
            steps {
                echo 'Building'
                sh 'mvn -B -U -DskipTests -P jenkins clean install'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Testing'
//                 sh 'mvn -B test'
            }
//             post {
//                 always {
//                     junit '**/target/surefire-reports/*.xml'
//                 }
//             }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying'
//                 sh 'mvn -B deploy'
            }
        }
    }
}
