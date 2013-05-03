package list;

public class AdjEntry{
	public DListNode partner;
	public DListNode start;
	public DListNode end;
	
	public AdjEntry(DListNode s, DListNode e){
		start = s;
		end = e;
	}
	
}
