// The file should not be named jenkins.groovy as that leads to a warning as there
// is already a jenkins package.
def releaseJobBuilder = new JobsBuilder(this).folder('lab/Johannes_Trattner/generated_job_test', {})
def baseImageJobBuilder = releaseJobBuilder.pipeline()

String basePath = 'example-path'
String repo = 'jenkins-training/tech-example-java'


baseImageJobBuilder.job("Docker_Base_Image") {
    htmlDescription(['Builds the Docker Base Image'])

    git(repo: 'https://github.com/Catrobat/Catroid', branch: '${gitBranch}', jenkinsfile: 'Jenkinsfile.baseDocker')

    parameters {
        gitParam('gitBranch') {
            description('Select the branch you want to build e.g. origin/master.')
            type('BRANCH')
            defaultValue('origin/master')
        }
    }

}
