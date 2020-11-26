package sma;

import application.DemarerElectionController;
import application.ElectionEnCour;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CandidatAgent extends GuiAgent{
	private DemarerElectionController gui;
	
	
	@Override
	protected void setup() {
		gui=(DemarerElectionController)getArguments()[0];
		gui.setCandidatAgent(this);
		System.out.println("Initialisation de l'agent "+this.getAID().getName());
		ParallelBehaviour parallelBehaviour=new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour(){

			@Override
			public void action() {
				// TODO Auto-generated method stub
				
				ACLMessage aclMessage=receive();
				if(aclMessage!=null) {
					switch(aclMessage.getPerformative()) {
						case ACLMessage.INFORM:
							if(gui.tempsEcoule()==false) {
							System.out.println("Message de "+aclMessage.getSender().getLocalName());
							sendListeCandidat(aclMessage.getSender().getLocalName(), "Democrate");
							sendListeCandidat(aclMessage.getSender().getLocalName(), "Republicain");
							sendListeCandidat(aclMessage.getSender().getLocalName(), "Abstention");}
							else {
								notificationFinDeVote(aclMessage.getSender().getLocalName());
							}
							break;
						case ACLMessage.CONFIRM:
							if(gui.tempsEcoule()==false) {
							GuiEvent guiEvent=new GuiEvent(this, 1);
							guiEvent.addParameter(aclMessage.getContent());
							guiEvent.addParameter(aclMessage.getOntology());
							gui.viewMessage(guiEvent);
							notificationVote(aclMessage.getSender().getLocalName());}
							else {
								notificationFinDeVote(aclMessage.getSender().getLocalName());
							}
							break;
						default:
							break;
					}
				}
				
				
		}});
		
		parallelBehaviour.addSubBehaviour(new OneShotBehaviour() {
			
			@Override
			public void action() {
				try {
					DFAgentDescription dfa=new DFAgentDescription();
					dfa.setName(getAID());
					ServiceDescription sd=new ServiceDescription();
					sd.setType("Conseil");
					sd.setName("conseil-electoral");
					dfa.addServices(sd);
					DFService.register(myAgent, dfa);
				} catch (FIPAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
			
	}
	
	public void sendListeCandidat(String sender,String candidat) {
		ACLMessage aclMessage=new ACLMessage(ACLMessage.PROPOSE);
		aclMessage.setContent(candidat);
		aclMessage.addReceiver(new AID(sender,AID.ISLOCALNAME));
		send(aclMessage);
	}
	
	public void notificationVote(String sender) {
		ACLMessage aclMessage=new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
		aclMessage.setContent("Vote reussi");
		aclMessage.addReceiver(new AID(sender,AID.ISLOCALNAME));
		send(aclMessage);
	}
	
	public void notificationFinDeVote(String sender) {
		ACLMessage aclMessage=new ACLMessage(ACLMessage.INFORM);
		aclMessage.setContent("Le temps pour l'election est ecoule");
		aclMessage.addReceiver(new AID(sender,AID.ISLOCALNAME));
		send(aclMessage);
	}
	
	@Override
	public void onGuiEvent(GuiEvent guiEvent) {
		// TODO Auto-generated method stub
		if(guiEvent.getType()==1) {
			ACLMessage aclMessage=new ACLMessage(ACLMessage.PROPOSE);
			String livre=guiEvent.getParameter(0).toString();
			aclMessage.setContent(livre);
			aclMessage.addReceiver(new AID("electeur",AID.ISLOCALNAME));
			//send(aclMessage);
		}
	}

}
