import unittest
from LCA import Node, findLCA
import LCA


class TestingLCA(unittest.TestCase):
#Building a basic binary tree and then testing to see if it finds the correct ancestor
    def test_basicGraph(self):
        #assert(False)
        root = Node(1)
        root.left = Node(2)
        root.right = Node(3)
        root.right.right=Node(3)
        root.right.right.right=Node(4)
        root.left.left=Node(5)
        root.left.right=Node(6)

        result=findLCA(root,5,4)
        self.assertEqual(result,1)

    def test_emptyGraaph(self):
        #assert(False)
        root = None

        result=findLCA(root,5,4)
        self.assertEqual(result,-1)
        


    def test_sameNodeLCA(self):
        #assert(False)
        root = Node(1)

        result=findLCA(root,1,1)
        self.assertEqual(result,1)    
if __name__=='__main__':
    unittest.main()        





