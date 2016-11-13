<node>
	id:0
	playerDecisionNeeded:false
	children:[(1,100)]
	
	<text>
		You have decided to try and smuggle some Lyme antibiotics across the border from Maine.
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:1
	playerDecisionNeeded:false
	children:[(2,100)]
	
	<text>
		For questionable reasons, it is illegal to bring this medicine into New Brunswick from the US.
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:2
	playerDecisionNeeded:false
	children:[(3,100)]
	
	<text>
		If you are caught, the border guards will not be happy! :( 
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:3
	playerDecisionNeeded:false
	children:[(4,100)]
	
	<text>
		What a bright and beautiful day to smuggle some antibiotics! 
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:4
	playerDecisionNeeded:false
	children:[(5,100)]
	
	<text>
		You have driven up to the border crossing, a border guard approaches your car... 
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:5
	playerDecisionNeeded:true
	children:[(6,50),(7,50)]
	
	<text>
		Would you like to try and distract the guard?
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:6
	playerDecisionNeeded:false
	children:[(8,50)]
	
	<text>
		The guard grows suspicious...
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:7
	playerDecisionNeeded:false
	children:[(15.50),(8,50)]
	
	<text>
		The guard smiles and lets you by, as you start to drive he glances over...
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:8
	playerDecisionNeeded:false
	children:[(9,90),(15,10)]
	
	<text>
		...The guard decides to search your car...
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:9
	playerDecisionNeeded:true
	children:[(10,50),(14,50)]
	
	<text>
		Rats! You got caught!! Do you want to jump out of the car and make a run for it?
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:10
	playerDecisionNeeded:false
	children:[(11,40),(12,40),(13,20)]
	
	<text>
		You leap from the car and sprint towards the nearest Canadian tree line!
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:11
	playerDecisionNeeded:false
	children:[(14,100)]
	
	<text>
		You are immediately tackled and arrested. :(
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:12
	playerDecisionNeeded:false
	children:[(15,100)]
	
	<text>
		Using your ninja skills you dodge and weave past Canadian border security and escape into the forest!
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:13
	playerDecisionNeeded:false
	children:[(15,100)]
	
	<text>
		A wild moose breaks from the forest, scaring away the border security, you leap unto its back and ride it to safety!
	</text>
	
	<reward>
		item:
		health:
		streetCred:
	</reward>
</node>

<node>
	id:14
	playerDecisionNeeded:false
	children:[]
	
	<text>
		You lost the antibiotics...let's hope no one dies as a result.
	</text>
	
	<reward>
		item:
		health:
		streetCred:-300
	</reward>
</node>

<node>
	id:15
	playerDecisionNeeded:false
	children:[]
	
	<text>
		Success!!! You made it out with the antibiotics!
	</text>
	
	<reward>
		item:
		health:40
		streetCred:500
	</reward>
</node>