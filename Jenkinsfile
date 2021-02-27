pipeline {
    agent none

    stages {
        stage('Build Java 8') {
            agent {
                docker {
                    image 'maven:3-openjdk-8'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Building..'
                sh 'mvn -pl . clean install'
                dir('java') {
                    echo "Building Java Utils..."
                    sh 'mvn clean package'
                }
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

        stage('Build Java 11') {
            agent {
                docker {
                    image 'maven:3-openjdk-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Building..'
                sh 'mvn -pl . clean install'
                dir('java') {
                    echo "Building Java Utils..."
                    sh 'mvn clean package'
                }
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
