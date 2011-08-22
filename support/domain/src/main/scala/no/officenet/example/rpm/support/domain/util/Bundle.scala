package no.officenet.example.rpm.support.domain.util

object Bundle extends ResourceBundleNameEnum {
	// The global resource-bundle. Holds texts for general use. Used in GlobalTexts
	val GLOBAL = BundleName("no.officenet.example.rpm.resources.globalResources")

	// Holds texts for Project (D) domain object fields. Used in ProjectTexts.D
	val PROJECT_D = BundleName("no.officenet.example.rpm.resources.projectDomainResources")

	// Holds texts for Project (D) domain object fields. Used in ProjectTexts.D
	val PET_D = BundleName("no.officenet.example.rpm.resources.petDomainResources")

	// Holds texts for Project (V) views (project-related pages). Used in ProjectTexts.V
	val PROJECT_V = BundleName("no.officenet.example.rpm.resources.projectViewResources")

	// Holds texts for Activity (D) domain object fields. Used in ActivityTexts.D
	val ACTIVITY_D = BundleName("no.officenet.example.rpm.resources.activityDomainResources")
}
