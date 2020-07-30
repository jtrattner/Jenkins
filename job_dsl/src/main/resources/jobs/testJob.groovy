// The file should not be named jenkins.groovy as that leads to a warning as there
// is already a jenkins package.
//def jobBuilder = new JobsBuilder(this).folder('', {})
//def jobBuilder = new JobsBuilder(this)
def baseImageJobBuilder = new JobsBuilder(this).folder('lab/Johannes_Trattner').pipeline()


baseImageJobBuilder.job("Build-Standalone_Test") {
    htmlDescription(['Builds a Catroid APP as a standalone APK.'])

    // !! DO NOT give Anonymous-Users read permission, otherwise the upload-token would be spoiled
    jenkinsUsersPermissions(Permission.JobRead)

    parameters {
        stringParam('DOWNLOAD', 'https://share.catrob.at/pocketcode/download/821.catrobat', 'Enter the Project ID you want to build as standalone')
        stringParam('SUFFIX', 'standalone', '')
       /* password {
            name('UPLOAD')
            setDefaultValue('')
            description('upload url for webserver\n\nSyntax of the upload value is of the form\n' +
                    'https://pocketcode.org/ci/upload/1?token=UPLOADTOKEN')
        }*/
        nonStoredPasswordParam('myParameterName', 'my description')
    }

    // The authentication token should not be on github.
    // That means it cannot be hardcode here.
    // At the same time this information should be visible in the job itself.
    // A workaround to achieve this is to store the information in global properties on jenkins master.
    def token = GLOBAL_STANDALONE_AUTH_TOKEN

    authenticationToken(token)
    git(repo: 'https://github.com/Catrobat/Catroid', branch: 'master', jenkinsfile: 'Jenkinsfile.BuildStandalone')
}
