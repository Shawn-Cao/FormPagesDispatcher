package com.xcao.formsDispatcher.conf

forms {  //to be analysed and assemble workflows
	defaults {
		identifiers = 'accountNumber'
	}
	aShortFlow {
		controller = 'aShortFlow'
		action = 'aShortFlowPage'
		entryPoint = 'aShortFlow.gsp'  //landing page  //1. analysis landing page or 2. give requirement to find best landing page?
		identifiers = 'accountNumber'
		toGather = 'supprise'
		dependency = ''

		supprise = '12345633321'
	}
	eligibility {
		controller = 'jump'
		action = 'eligibility'
		entryPoint = 'eligibility.gsp'  //landing page
	}
	drpTradeIn {
		controller = 'drp'
		action = 'launchDrp'
	}
	jumpTradeIn {
		controller = 'tradeInDevices'
		action = 'quote'
	}
	shopping {
		requires = 'TradeIn'
		collecting = 'handset'
	}

	checkOut {
		alias = 'regularUpgradeCheckout, JumpUpgradeCheckOut'
		identifiers = 'accountNumber, mobileNumber'
		requires = 'handset, plan, e911'
	}
}

workflows {  //short cut for known workflows
	aShortFlow = 'aShortFlow'
	regularUpgrade = 'eligibility=>drpTradeIn'
	jumpUpgrade = 'eligibility=>jumpTradeIn'
}
