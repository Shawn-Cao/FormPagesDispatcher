package com.xcao.formsDispatcher.conf

forms {  //to be analysed and assemble workflows
	defaults {
		identifiers = 'accountNumber'
	}
	changeSim {
		controller = 'simChange'
		action = 'showSimChangePage'
		entryPoint = 'deviceSubscriberPostpaid.gsp'  //landing page  //1. analysis landing page or 2. give requirement to find best landing page?
		identifiers = 'accountNumber'
		toGather = 'newSimNumber'
		dependency = ''

		newSimNumber = '12345633321'
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
	e911 {
	}
	regularUpgradeCheckOut {
		alias = 'regularUpgradeCheckout, JumpUpgradeCheckOut'
		identifiers = 'accountNumber, mobileNumber'
		requires = 'handset, plan, e911'
	}
}

workflows {  //short cut for known workflows
	changeSim = 'changeSim'
	regularUpgrade = 'eligibility=>drpTradeIn'
	jumpUpgrade = 'eligibility=>jumpTradeIn'
}