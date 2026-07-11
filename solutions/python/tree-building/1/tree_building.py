class Record:
    def __init__(self, record_id: int, parent_id: int):
        self.record_id = record_id
        self.parent_id = parent_id
class Node:
    def __init__(self, node_id: int):
        self.node_id = node_id
        self.children = []
    def __eq__(self, other):
        if not isinstance(other, Node):
            return False
        return (
            self.node_id == other.node_id
            and sorted(self.children, key=lambda x: x.node_id)
            == sorted(other.children, key=lambda x: x.node_id)
        )
    def __repr__(self):
        return f"Node({self.node_id}, children={self.children})"
def BuildTree(records):
    if not records:
        return None
    # Sort by record_id
    records.sort(key=lambda r: r.record_id)
    # Validate IDs start at 0 and are continuous
    for i, record in enumerate(records):
        if record.record_id != i:
            raise ValueError("Record id is invalid or out of order.")
    # Validate root record
    root = records[0]
    if root.parent_id != 0:
        raise ValueError("Node parent_id should be smaller than its record_id.")
    # Validate all other records
    for record in records[1:]:
        if record.record_id == record.parent_id:
            raise ValueError("Only root should have equal record and parent id.")
        if record.record_id < record.parent_id:
            raise ValueError("Node parent_id should be smaller than its record_id.")
    # Build node dictionary
    nodes = {record.record_id: Node(record.record_id) for record in records}
    # Attach children
    for record in records[1:]:
        parent = nodes[record.parent_id]
        parent.children.append(nodes[record.record_id])
    return nodes[0]