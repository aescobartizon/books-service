node {
   def mvnHome
   def openshiftHome
   def app = 'anescobar/books-service'
   def appPort = 'dc/books-service --port=8081'
   
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/aescobartizon/books-service'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.
      mvnHome = tool 'M3'
      openshiftHome = tool 'Openshift'
   }
   
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" clean/)
         bat(/"${mvnHome}\bin\mvn" install -Dmaven.test.skip=true/)
      }
   }
   stage('Test Software Quality') {
       bat(/"${mvnHome}\bin\mvn" sonar:sonar/)
   }
   stage('Make Docker Image') {
       bat(/"${mvnHome}\bin\mvn" dockerfile:build/)
   }

   stage('Push Docker Image') {
       //bat(/"${mvnHome}\bin\mvn" dockerfile:push/)
   }
   stage('Login openshift') {
         bat(/"${openshiftHome}\oc" login master.des.openshift --insecure-skip-tls-verify=true --username=aescobart --password=oc+aescobart /)
         bat(/"${openshiftHome}\oc" project backoffice-poc /)
   }
   stage('Deployment APP') {
         bat(/"${openshiftHome}\oc" new-app ${app} /)
         bat(/"${openshiftHome}\oc" expose ${appPort} /)
   }
   stage('Create Service') {
         bat(/"${openshiftHome}\oc" expose ${appPort} /)
   }
}
