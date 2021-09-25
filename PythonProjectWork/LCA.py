
#This is a basic  attempt at the LCA problem in a language I havent used before. I am not familiar with Python 
#so I used an implementation I found online as the basis for my code since the
#lecturer said we were allowed to do so
#
#
#
#
#
#



#The node class
class Node:
    def __init__(self, key):
        self.key =  key
        self.left = None
        self.right = None


def findPath(node, path, key):
 
    #if(root==null)
    if node is None:
        return False

    #ArrayList 1||2 //adds current Nodes to the list
    path.append(node.key)

    #if(node.key==key){return true}
    if node.key == key:
        return True


  #Should keep going left and if it finds a null point then it goes back one step and goes right then continues going left
  #  (if node.left != null && findPath(Node.left,key)==true)|| node.right==null && findPath(Node,key)==true){return true}
    if (((node.left != None) and (findPath(node.left, path, key))) or ((node.right!= None) and findPath(node.right, path, key))):
        return True
    path.pop()#Remove the current Node from the path
    return False
 
def findLCA(root, n1, n2):
 
    path1 = []          #Arraylist1
    path2 = []          #Arraylist2
        

        #if(findPath(root,arrayList1,node1)==false || findPath(root,arrayList2,node2)==false){return false}
    if (not findPath(root, path1, n1) or not findPath(root, path2, n2)):
        return -1


    #int i;
    #for(i=0;i<ArrayList1.size() && i< ArrayList2.size();i++)
    #{
    #  if(ArrayList.get(i) !=ArrayList.get(i)){break;}
    #  else{i=i+1;}    
    #}
    #return ArrayList1.get(i-1);
    #
    #
    i = 0
    while(i < len(path1) and i < len(path2)):
        if path1[i] != path2[i]:
            break
        i =i+1
    return path1[i-1]
 


 #Main Line
root = Node(1)
root.left = Node(2)
root.right = Node(3)
root.right.right=Node(3)
root.right.right.right=Node(4)
root.left.left=Node(5)
root.left.right=Node(6)

print(findLCA(root,5,4))
print(findLCA(root,3,4))
print(findLCA(root,5,6))





