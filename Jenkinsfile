pipeline {
    agent {
      label 'maven-jdk11-openj9'
    }

    environment {
        POM_VERSION = readMavenPom().getVersion()
    }

    stages {
        stage("Initialization") {
            steps {
                buildName "#${BUILD_NUMBER} ${POM_VERSION}"
                scmSkip(deleteBuild: true, skipPattern:'.*\\[ci skip\\].*')
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B -U -DskipTests -P jenkins clean install'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }
    }
}
