package ResearchSystem ;
import java.util.*;

import ResearchPapers.ResearchPaper;

public class ResearchProject {
    public String topic;
    public String definition;
    public ResearchPaper publishedPapers;
    public Vector<Researcher> members;
    {
        members = new Vector<>();

    }
    
    public ResearchProject(String topic, String definition) {
    	this.topic = topic;
    	this.definition = definition;
    }
  
    public ResearchProject() {
    }
  
    public void addParticipant(Researcher member) {
        if (!members.contains(member)) {
            members.add(member);
            member.addProject(this);
            System.out.println("Participant added successfully: " + member.getName());
        } else {
            System.out.println("Participant already exists in the project.");
        }
    }

    public void removeParticipant(Researcher member) {
        if (members.contains(member)) {
            members.remove(member);
            member.removeProject(this);
            System.out.println("Participant removed successfully: " + member.getName());
        } else {
            System.out.println("Participant not found in the project.");
        }
    }
    
    public static ResearchProject createProject(String topic, String definition) {
        ResearchProject project = new ResearchProject();
        project.topic = topic;
        project.definition = definition;
        System.out.println("Project created with topic: " + topic + definition);
        return project;
    }
    
    public String getTopic() {
    	return topic;
    }
    
    public void setTopic(String topic) {
    	this.topic = topic;
    }
    
    public String getDefinition() {
    	return definition;
    }
    
    public void setDefinition(String definition) {
    	this.definition = definition;
    }
    
    public ResearchPaper getPublishedPapers() {
        return publishedPapers;
    }

    public void setPublishedPapers(ResearchPaper publishedPapers) {
        this.publishedPapers = publishedPapers;
    }
    
    public Vector<Researcher> getMembers() {
        return members;
    }

    public void setMembers(Vector<Researcher> members) {
        this.members = members;
    }
    
    public String toString() {
    	return "Research project details: " + topic + definition + publishedPapers + members;
    }
}


