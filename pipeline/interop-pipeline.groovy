/*
    Pipeline script for triggering scheduled cron job.
*/

def nodeName = "centos-7"
def sharedLib
def buildArtifacts
def release


node(nodeName) {
    try {
        timeout(unit: "MINUTES", time: 30) {
            stage('Install prereq') {
                if (env.WORKSPACE) { sh script: "sudo rm -rf * .venv" }
                checkout(
                    scm: [
                        $class: 'GitSCM',
                        branches: [[name: 'refs/remotes/origin/paral_script']],
                        extensions: [
                            [
                                $class: 'CleanBeforeCheckout',
                                deleteUntrackedNestedRepositories: true
                            ],
                            [
                                $class: 'WipeWorkspace'
                            ],
                            [
                                $class: 'CloneOption',
                                depth: 1,
                                noTags: true,
                                shallow: true,
                                timeout: 10,
                                reference: ''
                            ]
                        ],
                        userRemoteConfigs: [[
                            url: 'https://github.com/red-hat-storage/cephci.git'
                        ]]
                    ],
                    changelog: false,
                    poll: false
                )

                // prepare the node
                sharedLib = load("${env.WORKSPACE}/pipeline/vars/lib.groovy")
                sharedLib.prepareNode()
            }
        }

        stage('Run') {
           sh script: "sh ${env.WORKSPACE}/pipeline/scripts/4/interop/test-ceph-features-async.sh --osp-cred $HOME/osp-cred-ci-2.yaml --inventory conf/inventory/rhel-9-latest.yaml --add-repo https://download.eng.bos.redhat.com/rhel-9/nightly/RHEL-9/latest-RHEL-9/repofile.repo --platform rhel-9"
        }

    } catch(Exception err) {
        if (currentBuild.result != "ABORTED") {
            // notify about failure
            currentBuild.result = "FAILURE"
            def failureReason = err.getMessage()
        }
    }
}
