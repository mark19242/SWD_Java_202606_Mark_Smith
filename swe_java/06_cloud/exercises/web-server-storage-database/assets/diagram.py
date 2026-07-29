## This script generates the diagram for this demo with the diagramming tool
## https://github.com/mingrammer/diagrams

from diagrams import Cluster, Diagram
from diagrams.generic.device import Tablet
from diagrams.gcp.api import APIGateway
from diagrams.gcp.compute import GCE, Functions, ContainerOptimizedOS
from diagrams.gcp.network import LoadBalancing
from diagrams.gcp.database import Datastore
from diagrams.gcp.storage import Storage

with Diagram("Web Site with Public Image Storage and Private Database", show=False):
    lb = LoadBalancing("Load Balancer")

    with Cluster("Instance Group"):
        svc_group = [GCE("Zone 1\nWeb-1"),
                     GCE("Zone 2\nWeb-2"),
                     GCE("Zone 3\nWeb-3")]
        
    db = Datastore("Cloud Datastore\nDB of Legends")
    storage = Storage("Cloud Storage\nImages")

    fn = Functions("Cloud Function\nGet Image Legends")
    fn >> db
    api = APIGateway("API Gateway\nHTTPS legends")
    api >> fn

    it = ContainerOptimizedOS("Instance Template")
    image = ContainerOptimizedOS("Custom Image\nAdd our Web Files")
    market = ContainerOptimizedOS("Marketplace Image\nApache2")
    svc_group >> it >> image >> market

    user = Tablet("User")
    lb >> svc_group
    storage
    api

    user >> lb
    user >> api
    user >> storage
