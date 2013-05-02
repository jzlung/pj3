package list;

public class AdjEntry{
	public DListNode partner;
	public DListNode start;
	public DListNode end;
	
	public AdjEntry(DListNode p, DListNode s, DListNode e){
		partner = p;
		start = s;
		end = e;
	}
}
