from exceptions import Exception

class DLinkedNode:
    def __init__(self, key, value):
        self.val = value
        self.key = key
        self.prev = None
        self.next = None

class LRUCache:

    # @param capacity, an integer
    def __init__(self, capacity):
        self.timeseries_head = None
        self.timeseries_tail = None
        self.keyval_map = {}
        self.cap = capacity
        if capacity <= 0:
            raise Exception("Cache capacity must be greater than 0!")

    # @return an integer
    def get(self, key):
        if key in self.keyval_map:
            node = self.keyval_map[key]
            self._setMRU(node)
            return node.val
        else:
            return -1

    # @param key, an integer
    # @param value, an integer
    # @return nothing
    def set(self, key, value):
        if key in self.keyval_map:
            node = self.keyval_map[key]
            self._setMRU(node)
            node.val = value
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
                    temp = self.timeseries_head
                    self.timeseries_head = temp.next
                    self.timeseries_head.prev = None
                    temp.next = None
            #append node at tail
            if self.timeseries_tail == None: #if empty cache
                self.timeseries_head, self.timeseries_tail = node, node
            else:
                self.timeseries_tail.next = node
                node.prev = self.timeseries_tail
                self.timeseries_tail = node
            self.keyval_map[key] = node
        
    def _setMRU(self, node):
        ''' set a node as the most-recently-utilized (MRU) node, by moving it to the tail'''
        if not node == self.timeseries_tail:
            if node == self.timeseries_head:
                self.timeseries_head = node.next
            else:
                #cut the node out of the list
                temp = node.prev
                temp.next = node.next
                temp.next.prev = temp
            #append node to the tail of the list
            self.timeseries_tail.next = node
            node.prev = self.timeseries_tail
            node.next = None
            self.timeseries_tail = node       

    def println(self):
        print "==========printing all============"
        #print self.keyval_map
        node = self.timeseries_head
        line = ""
        while node:
            line += str(node.key) + "->"
            node = node.next
        print line + "*"
        print("head = %s"%str(self.timeseries_head.key))
        print("tail = %s"%str(self.timeseries_tail.key))