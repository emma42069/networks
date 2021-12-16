DESCRIPTION:
Network Simulator (Version 2), widely known as NS2,simulation tool which is meant for studying
dynamic nature of communication i.e. for both wired and wireless network functions and for routing
algorithms, TCP, UDP protocols. Basically it provides specific way of simulating such network
protocols.
TCP
The important protocol is Transmission Control Protocol (TCP) used for transmission in a network.
Internet widely uses TCP protocol for data transmission in communication network. In order to
perform transmission connection is established between the client and the server. Connection is
initiated by the Client and sends Sequence number along with the segment. So the Server
acknowledges it back with its own Sequence number and ACK of client’s segment which is one more
than client’s Sequence number. Client after receiving ACK of its segment sends an acknowledgement
of Server’s response.
UDP
The User Datagram Protocol (UDP) is simplest Transport Layer communication protocol available of
the TCP/IP protocol suite. Communication mechanism involved is minimum. UDP is an unreliable
transport protocol which uses IP services to provide best delivery mechanism. In UDP, there is no
generation of acknowledgement of received packets to sender and senders do not wait for any
acknowledgement in turn of packet. This makes UDP protocol unreliable on processing. UDP is
implemented where packet acknowledgement have same significance on bandwidth as that actual
data. In case of video streaming, thousands of packets are generated towards its users.
Acknowledging all those packets is troublesome and which results in huge amount of bandwidth
wastage.
METHODOLOGY FOR PERFORMANCE MEASURES:
The network is simulated using NS2 software. In order to simulate the algorithm contains the
following steps
1. New simulator is created.
2. Files are opened to store simulation results.
3. Nodes are created such as n0, n1, n2, n3, n4 and n5.
4. Links are established between all the nodes present
5. Transport agents are created, transport agents might be TCP, UDP or variants of TCP.
6. Connection is established between the transport agents used in the network.
7. Later traffic agents are created.
8. Once the traffic agents are created finish procedure is written.
9. Simulation time or the run time is set.
10. Finally, Simulation is performed

Performance measure Using TCP
In this scenario, for the above proposed network TCP protocol is connected to all source nodes and
the bandwidth of links are kept constant. Throughputs and efficiency are calculated for bandwidth =
0.5mb and the efficiency is 75%

Performace measures Using UDP
In this scenario, for the above proposed network UDP protocol is connected to all source nodes and
the bandwidth
of links are kept constant. Throughput and efficiency are calculated.