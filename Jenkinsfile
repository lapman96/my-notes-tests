pipeline {
    agent { label 'macOS'}
    parameters {
      choice choices: ['prod'], description: 'Select an environment for running tests', name: 'ENVIRONMENT'
      choice choices: ['chrome', 'edge'], description: 'Select a browser for running tests', name: 'BROWSER'
      text defaultValue: 'P1', description: 'Specify the test tags to run, separated by commas.', name: 'TAGS'
    }
    stages {
        stage('Test') {
            steps {
                echo "Running tests in the selected environment: ${params.ENVIRONMENT}, browser: ${params.BROWSER}, with tags: ${params.TAGS}"
                sh "./gradlew test -Denv=${params.ENVIRONMENT} -Dbrowser=${params.BROWSER} -Ptags=\"${params.TAGS}\" -Drun_remotely=false"
            }
        }
    }
}