package sma;

import java.util.Map;

import Electeur.ElecteurContainer;
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

public class ElecteurAgent extends GuiAgent{
	private ElecteurContainer gui;
	
	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		gui=(ElecteurContainer) getArguments()[0];
		gui.setElecteurAgent(this);
		System.out.println("Initialisation de l'agent "+this.getAID().getName());
		ParallelBehaviour parallelBehaviour=new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {
				// TODO Auto-generated method stub
//				MessageTemplate messageTemplate=MessageTemplate.or(
//				MessageTemplate.MatchPerformative(ACLMessage.PROPOSE), 
//				MessageTemplate.MatchPerformative(ACLMessage.INFORM));
//				ACLMessage message=receive(messageTemplate);
				ACLMessage aclMessage=receive();
				if(aclMessage!=null) {
					GuiEvent guiEvent=new GuiEvent(this, 1);
					switch(aclMessage.getPerformative()) {
					case ACLMessage.PROPOSE:
						System.out.println("Reception d'un message "+aclMessage.getContent());
						guiEvent.addParameter(aclMessage.getContent());
						gui.viewMessageDemocrate(aclMessage.getContent());
						break;
					case ACLMessage.ACCEPT_PROPOSAL:
						guiEvent.addParameter(aclMessage.getContent());
						gui.notificationVote(aclMessage.getContent());
						break;
					case ACLMessage.INFORM:
						guiEvent.addParameter(aclMessage.getContent());
						gui.notificationTempsVote(aclMessage.getContent());
						break;
					default:
						break;
					}
					//System.out.println(""+aclMessage.getContent());
				}
			}
			
		});
		
		parallelBehaviour.addSubBehaviour(new OneShotBehaviour() {
			
			@Override
			public void action() {
				try {
					DFAgentDescription dfa=new DFAgentDescription();
					dfa.setName(getAID());
					ServiceDescription sd=new ServiceDescription();
					sd.setType("Electeur");
					sd.setName("electeur");
					dfa.addServices(sd);
					DFService.register(myAgent, dfa);
				} catch (FIPAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void onGuiEvent(GuiEvent guiEvent) {
		// TODO Auto-generated method stub
		if(guiEvent.getType()==1) {
			ACLMessage aclMessage=new ACLMessage(ACLMessage.CONFIRM);
			String livre=guiEvent.getParameter(0).toString();
			aclMessage.setContent(livre);
			aclMessage.addReceiver(new AID("CE",AID.ISLOCALNAME));
			send(aclMessage);
		}
	}
	
	public void onGuiEventAsk(GuiEvent guiEvent) {
		// TODO Auto-generated method stub
		if(guiEvent.getType()==1) {
			ACLMessage aclMessage=new ACLMessage(ACLMessage.INFORM);
			String livre=guiEvent.getParameter(0).toString();
			aclMessage.setContent(livre);
			aclMessage.addReceiver(new AID("CE",AID.ISLOCALNAME));
			send(aclMessage);
		}
	}
	
	
}
