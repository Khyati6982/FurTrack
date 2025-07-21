pipeline {
	agent any
	
	environment {
		DOCKER_IMAGE = 'furtrack:latest'
	}
	
	stages {
		stage('Checkout Code') {
			steps {
				git url: 'https://github.com/Khyati6982/FurTrack.git', branch: 'master'
			}
		}
		
		stage('Build with Maven') {
			steps {
				sh 'mvn clean package'
			}
		}
		
		stage('Build Docker Image') {
			steps {
				script {
					docker.build("${DOCKER_IMAGE}")
				}
			}
		}
		
		stage('Push Docker Image') {
			when {
				expression { return false }
			}
			steps {
				echo 'Image push disabled for now (no registry configured)'
			}
		}
		
		stage('Cleanup') {
			steps {
				echo 'Pipeline complete. Artifacts ready for deployment!'
			}
		}
	}
	
	post {
		success {
			echo 'FurTrack build and dockerize: SUCCESS!'
		}
		failure {
			echo 'Pipeline failed. Check logs for clues'
		}
	}
}