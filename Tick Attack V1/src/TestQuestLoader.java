import static org.junit.Assert.*;

import org.junit.Test;

public class TestQuestLoader {

	@Test
	public void test() {
		QuestDecisionPoint startPoint = new QuestDecisionPoint(false,false,"You have decided to try and smuggle some Lyme antibiotics accross the border from Maine.");
		QuestDecisionPoint secondPoint = new QuestDecisionPoint(false,false,"For questionable reasons, it is illegal to bring this medicine into New Brunswick from the US.");
		QuestDecisionPoint thirdPoint = new QuestDecisionPoint(false,false,"If you are caught, the border guards will not be happy! :( ");
		QuestDecisionPoint fourthPoint = new QuestDecisionPoint(false,false,"What a bright and beautiful day to smuggle some antibiotics!");
		QuestDecisionPoint fifthPoint = new QuestDecisionPoint(false,false,"You have driven up to the border crossing, a border guard approaches your car...");
		QuestDecisionPoint sixthPoint = new QuestDecisionPoint(true,false,"Would you like to try and distract the guard?");
		QuestDecisionPoint sixthPointChildTrue = new QuestDecisionPoint(false,false,"The guard grows suspicious...");
		QuestDecisionPoint sixthPointChildFalse = new QuestDecisionPoint(false,false,"The guard smiles and lets you by, as you start to drive he glances over...");
		QuestDecisionPoint seventhPoint = new QuestDecisionPoint(false,false,"...The guard decides to search your car...");
		QuestDecisionPoint caughtPoint = new QuestDecisionPoint(true,false,"Rats! You got caught!! Do you want to jump out of the car and make a run for it?");
		QuestDecisionPoint caughtPointTrue1 = new QuestDecisionPoint(false,false,"You leap from the car and sprint towards the nearest Canadian tree line!");
		QuestDecisionPoint caughtPointTrue2a = new QuestDecisionPoint(false,false,"You are immediately tackled and arrested. :(");
		QuestDecisionPoint caughtPointTrue2b = new QuestDecisionPoint(false,false,"Using your ninja skills you dodge and weave past Canadian border security and escape into the forest!");
		QuestDecisionPoint caughtPointTrue2c = new QuestDecisionPoint(false,false,"A wild moose breaks from the forest, scaring away the border security, you leap unto its back and ride it to safety!");
		QuestDecisionPoint failurePoint = new QuestDecisionPoint(false,false,"You lost the antibiotics...let's hope no one dies as a result.");
		QuestDecisionPoint victoryPoint = new QuestDecisionPoint(false,true,"Success!!! You made it out with the antibiotics!");
		
		failurePoint.setSCEffect(-300);
		victoryPoint.setSCEffect(500);
		victoryPoint.setHealthEffect(40);
		
		caughtPointTrue2c.addChild(victoryPoint, 100);
		caughtPointTrue2b.addChild(victoryPoint, 100);
		caughtPointTrue2a.addChild(failurePoint, 100);
		caughtPointTrue1.addChild(caughtPointTrue2a, 40);
		caughtPointTrue1.addChild(caughtPointTrue2b, 40);
		caughtPointTrue1.addChild(caughtPointTrue2c, 20);
		caughtPoint.addChild(caughtPointTrue1, 50);
		caughtPoint.addChild(failurePoint, 50);
		seventhPoint.addChild(caughtPoint, 90);
		seventhPoint.addChild(victoryPoint, 10);
		sixthPointChildTrue.addChild(seventhPoint, 50);
		sixthPointChildFalse.addChild(victoryPoint, 50);
		sixthPointChildFalse.addChild(seventhPoint, 50);
		sixthPoint.addChild(sixthPointChildTrue, 50);
		sixthPoint.addChild(sixthPointChildFalse, 50);
		fifthPoint.addChild(sixthPoint, 100);
		fourthPoint.addChild(fifthPoint, 100);
		thirdPoint.addChild(fourthPoint, 100);
		secondPoint.addChild(thirdPoint, 100);
		startPoint.addChild(secondPoint, 100);
		
		QuestDecisionPoint loadedQuest = QuestLoader.load(String filePath);
		
		assertTrue(startPoint.equals(loadedQuest));
	}

}