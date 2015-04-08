from exceptions import Exception

#a doubly-linked list class
class DLinkedNode:
    def __init__(self, key, value):
        self.val = value
        self.key = key
        self.prev = None
        self.next = None

class LRUCache:

    # @param capacity, an integer
    def __init__(self, capacity):
        self.timeseries_head = None #head and tail of the DLink list
        self.timeseries_tail = None #tail of the list
        self.keyval_map = {} #{key->listNode}
        self.cap = capacity
        if capacity <= 0:
            raise Exception("Cache capacity must be greater than 0!")

    # @return an integer
    def get(self, key):
        if key in self.keyval_map:
            node = self.keyval_map[key]
            self.move2MRU(node)
            return node.val
        else:
            return -1

    # @param key, an integer
    # @param value, an integer
    # @return nothing
    def set(self, key, value):
        if key in self.keyval_map:
            self.keyval_map[key].val = value
            self.move2MRU(self.keyval_map[key])
        else:
            node = DLinkedNode(key, value)
            if len(self.keyval_map) >= self.cap:
                #remove LRU
                self.keyval_map.pop(self.timeseries_head.key)
                if self.timeseries_head == self.timeseries_tail:
                    #the last cache node has just been pop() out
                    self.timeseries_head, self.timeseries_tail = None, None
                else:
                    #remove the head node
                    self.timeseries_head = self.timeseries_head.next
                    self.timeseries_head.prev.next, self.timeseries_head.prev = None, None 
            #append node at tail
            if self.timeseries_tail == None: #if empty cache
                self.timeseries_head, self.timeseries_tail = node, node
            else:
                self.timeseries_tail.next = node
                node.prev = self.timeseries_tail
                self.timeseries_tail = node
            self.keyval_map[key] = node
        
    def move2MRU(self, node):
        ''' move a node to the tail, as the most-recently-utilized (MRU) node'''
        if not node == self.timeseries_tail:
            if node == self.timeseries_head:
                self.timeseries_head = node.next
            else:
                #cut the node out of the list
                node.prev.next, node.next.prev = node.next, node.prev
            #append node to the tail of the list
            self.timeseries_tail.next = node
            node.prev = self.timeseries_tail
            node.next = None
            self.timeseries_tail = node       

    def println(self):
        '''a helper function for debugging'''
        print "==========printing all============"
        node = self.timeseries_head
        line = ""
        while node:
            line += str(node.key) + "->"
            node = node.next
        print line + "*"
        print("head = %s"%str(self.timeseries_head.key))
        print("tail = %s"%str(self.timeseries_tail.key))