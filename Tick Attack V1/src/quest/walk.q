<randomGate>
	id:0
	playerDecisionNeeded:false
	children:[]
	Condition:CountCondition(5)
	possibleIdentities:[(1,0.25),(2,0.25),(3,0.25),(4,0.25)]
<randomGate>

<node>
	id:1
	playerDecisionNeeded:false
	children:[]
	
	<text>
		You stop to grab a bite. From amidst the crowd, a child wails like a banshee.
	<text/>
	
	<reward>
		item:
		health:-1
		streetCred:
	</reward>
<node>

<node>
	id:2
	playerDecisionNeeded:false
	children:[]
	
	<text>
		Sam stops to rest his legs.
	<text/>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
<node>

<node>
	id:3
	playerDecisionNeeded:false
	children:[]
	
	<text>
		Sam stops to rest his legs.
	<text/>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
<node>

<node>
	id:4
	playerDecisionNeeded:false
	children:[(5,0.5),(6,0.5)]
	
	<text>
		A man runs at you swooshing flyers in your face!
	<text/>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
<node>

<node>
	id:5
	playerDecisionNeeded:false
	children:[]
	
	<text>
		You successfully shoo him away!
	<text/>
	
	<reward>
		item:
		health:
		streetCred:5
	</reward>
<node>

<node>
	id:6
	playerDecisionNeeded:false
	children:[]
	
	<text>
		You regrettably grab a flier, and now it�s in your hand, and you don�t know what to do with it.
	<text/>
	
	<reward>
		item:
		health:
		streetCred:-5
	</reward>
<node>