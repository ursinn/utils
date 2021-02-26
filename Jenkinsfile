pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn -pl . clean install'
                dir('bukkit') {
                    echo "Building Bukkit Utils..."
                    sh 'mvn clean package'
                }
                dir('spigot') {
                    echo "Building Spigot Utils..."
                    sh 'mvn clean package'
                }
                dir('bungee') {
                    echo "Building Bungee Utils..."
                    sh 'mvn clean package'
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
                }
            }
        }
    }
}
