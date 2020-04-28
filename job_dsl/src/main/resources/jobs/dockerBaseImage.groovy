// The file should not be named jenkins.groovy as that leads to a warning as there
// is already a jenkins package.
def releaseJobBuilder = new JobsBuilder(this).folder('job/lab/job/Johannes_Trattner/generated_job', {})
def baseImageJobBuilder = releaseJobBuilder.pipeline()

String basePath = 'example-path'
String repo = 'jenkins-training/tech-example-java'


baseImageJobBuilder.job("Catroid_to_Google_Play_alpha") {
    htmlDescription(['test'])

}
