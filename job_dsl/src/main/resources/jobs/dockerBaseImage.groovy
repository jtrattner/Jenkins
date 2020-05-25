// The file should not be named jenkins.groovy as that leads to a warning as there
// is already a jenkins package.
//def jobBuilder = new JobsBuilder(this).folder('', {})
//def jobBuilder = new JobsBuilder(this)
def baseImageJobBuilder = new JobsBuilder(this).folder('lab/Johannes_Trattner').pipeline()


baseImageJobBuilder.job("Build_Docker_Base_Image") {
    htmlDescription(['Builds the docker base Image and pushes it to the dockerhub'])

    git(repo: 'https://github.com/Catrobat/Catroid', branch: '${gitBranch}', jenkinsfile: 'Jenkinsfile.baseDocker')

    parameters {
        gitParam('gitBranch') {
            description('Select the branch you want to build e.g. origin/master.')
            type('BRANCH')
            defaultValue('origin/master')
        }
    }
    nightly()

}
