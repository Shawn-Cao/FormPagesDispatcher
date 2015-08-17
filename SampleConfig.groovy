forms {
    changeSim.gsp {
	    newSimNumber
	}

}

workflows {
        upgradeRegular = 'eligibility,dradeIn,shopping'
	changeSim {
        entryPoint = deviceSubscriberPostpaid.gsp  //landing page  //1. analysis landing page or 2. give requirement to find best landing page?
		toGather = newSimNumber
        dependency //?
	}
	Jump {
		eligibility.gsp  //landing page
		
	}
}
