node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/oqs-providerport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/oqs-providerport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'This repository contains code to enable quantum-safe cryptography (QSC) in a standard OpenSSL (3.x) distribution by way of implementing a single shared library, the OQS'),
      string(name: 'BUILD_LINE', value: 'STABLE')
    ]
  }
}
