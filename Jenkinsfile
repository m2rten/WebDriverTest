pipeline {
	agent any
    stages {
        stage('Build') { 
            steps {
            	echo 'starting tests'
                mvn test
            }
        }
    }
}