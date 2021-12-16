DESCRIPTION:
    ns is an object oriented simulator, written in C++, with an OTcl interpreter as a frontend.The
simulator supports a class hierarchy in C++(also called the compiled hierarchy in this document),
    and a similar class hierarchy within the OTcl interpreter(also called the interpreted hierarchy in this document).The two hierarchies are closely related to each other;
from the user’ s perspective, there is
a one - to - one correspondence between a class in the interpreted hierarchy and one in the compiled
hierarchy.The root of this hierarchy is the class TclObject.Users create new simulator objects through
the interpreter;
these objects are instantiated within the interpreter, and are closely mirrored by a
corresponding object in the compiled hierarchy.The interpreted class hierarchy is automatically
established through methods defined in the class TclClass.User instantiated objects are mirrored
through methods defined in the class TclObject.There are other hierarchies in the C++code and OTcl
scripts;
these other hierarchies are not mirrored in the manner of TclObject.
CONCEPT OVERVIEW:
    ns uses two languages because simulator has two different kinds of things it needs to do .On
    one hand, detailed simulations of protocols requires a systems programming language which can
efficiently manipulate bytes, packet headers, and implement algorithms that run over large data sets.
For these tasks run - time speed is important and turn - around time(run simulation, find bug, fix bug,
    recompile, re - run) is less important.On the other hand, a large part of network research involves
slightly varying parameters or configurations, or quickly exploringa number of scenarios.In these
cases, iteration time(change the model and re - run) is more important.Since configuration runs once(at the beginning of the simulation), run - time of this part of the task is less important.ns meets both of these needs with two languages, C++and OTcl.C++is fast to run but slower to change, making it
suitable
for detailed protocol implementation.OTcl runs much slower but can be changed very
quickly(and interactively), making it ideal
for simulation configuration.ns(via tclcl) provides glue to
make objects and variables appear on both langauges.
SIMULATOR INITIALIZATION:
    When a new simulation object is created in tcl, the initialization procedure performs
the following operations: •initialize the packet format(calls create_packetformat)• create a scheduler(defaults to a calendar scheduler)• create a“ null agent”(a discard sink used in various places)
SCHEDULERS AND EVENTS:
    The simulator is an event - driven simulator.There are presently four schedulers available in
    the simulator, each of which is implemented using a different data structure: a simple linked - list,
    heap, calendar queue(
        default), and a special type call called“ real - time”.Each of these are described
below.The scheduler runs by selecting the next earliest event, executing it to completion, and
returning to execute the next event.Unit of time used by scheduler is seconds.Presently, the
simulator is single - threaded, and only one event in execution at any given time.If more than one
event are scheduled to execute at the same time, their execution is performed on the first scheduled–
first dispatched manner.Simultaneous events are not reordered anymore by schedulers(as it was in
    earlier versions) and all schedulers should yeild the same order of dispatching given the same input

    NODE BASICS:
    The basic primitive
for creating a node is
set ns[new Simulator]
$ns node
The instance procedure node constructs a node out of more simple classifier objects(Section 5.4).The
Node itself is a standalone class in OTcl.However, most of the components of the node are
themselves TclObjects.The typical structure of a(unicast) node is as shown in Figure 5.1.This simple
structure consists of two TclObjects: an address classifer(classifer_) and a port classifier(dmux_).The

function of these classifiers is to distribute incoming packets to the correct agent or outgoing link.All
nodes contain at least the following components: 
    an address or id_, monotonically increasing by 1(from initial value 0) across the simulation
namespace as nodes are created, 
a list of neighbors(neighbor_),
    BASIC COMMANDS IN NS2:
    set ns[new Simulator]: generates an NS simulator object instance, and assigns it to variable ns.The "Simulator"
object has member functions that do the following: 
    Create compound objects such as nodes and links(described later)
Connect network component objects created(ex.attach - agent)
Set network component parameters(mostly
    for compound objects)
Create connections between agents(ex.make connection between a "tcp"
    and "sink")
Specify NAM display options
Most of member functions are
for simulation setup(referred to as plumbing functions in the overview section) and scheduling, however some of them are
for the NAM display.
The "Simulator"
object member
function implementations are located in the "ns-2/tcl/lib/nslib.tcl"
file.
$ns color fid color: is to set color of the packets
for a flow specified by the flow id(fid).This
member
function of "Simulator"
object is
for the NAM display, and has no effect on the actual
simulation.
$ns namtrace - all file - descriptor: This member
function tells the simulator to record simulation
traces in NAM input format.It also gives the file name that the trace will be written to later
by the command $ns flush - trace.Similarly, the member
function trace - all is
for recording the
simulation trace in a general format.
proc finish {}: is called after this simulation is over by the command $ns at 5.0 "finish".In this

function, post - simulation processes are specified.

set n0[$ns node]: The member
function node creates a node.A node in NS is compound
object made of address and port classifiers(described in a later section).Users can create a
node by separately creating an address and a port classifier objects and connecting them
together.However, this member
function of Simulator object makes the job easier.To see how
a node is created, look at the files: "ns-2/tcl/libs/ns-lib.tcl"
andns - 2 / tcl / libs / ns - node.tcl ".
31
$ns duplex - link node1 node2 bandwidth delay queue - type: creates two simplex links of specified
bandwidth and delay, and connects the two specified nodes.In NS, the output queue of a
node is implemented as a part of a link, therefore users should specify the queue - type when
creating links.In the above simulation script, DropTail queue is used.If the reader wants to
use a RED queue, simply replace the word DropTail with RED.The NS implementation of a
link is shown in a later section.Like a node, a link is a compound object, and users can create
its sub - objects and connect them and the nodes.Link source codes can be found in "ns2/tcl/libs/ns-lib.tcl"
and "ns-2/tcl/libs/ns-link.tcl"
files.One thing to note is that you can
insert error modules in a link component to simulate a lossy link(actually users can make and insert any network objects).Refer to the NS documentation to find out how to do this.
    $ns queue - limit node1 node2 number: This line sets the queue limit of the two simplex links that
connect node1 and node2 to the number specified.At this point, the authors do not know how
    many of these kinds of member functions of Simulator objects are available and what they are.
Please take a look at "ns-2/tcl/libs/ns-lib.tcl"
and "ns-2/tcl/libs/ns-link.tcl", or NS.
$ns duplex - link - op node1 node2...: The next couple of lines are used
for the NAM display.To
see the effects of these lines, users can comment these lines out and
try the simulation.
Now that the basic network setup is done, the next thing to do is to setup traffic agents such
as TCP and UDP, traffic sources such as FTP and CBR, and attach them to nodes and agents
respectively.
set tcp[new Agent / TCP]: This line shows how to create a TCP agent.But in general, users can
create any agent or traffic sources in this way.Agents and traffic sources are in fact basic
objects(not compound objects), mostly implemented in C++and linked to OTcl.Therefore,
    there are no specific Simulator object member functions that create these object instances.To
create agents or traffic sources, a user should know the class names these objects(Agent / TCP,
    Agnet / TCPSink, Application / FTP and so on).This information can be found in the NS
documentation or partly in this documentation.But one shortcut is to look at the "ns2/tcl/libs/ns-default.tcl"
file.This file contains the
default configurable parameter value
settings
for available network objects.Therefore, it works as a good indicator of what kind of
    network objects are available in NS and what are the configurable parameters.
$ns attach - agent node agent: The attach - agent member
function attaches an agent object created
to a node object.Actually, what this
function does is call the attach member
function of specified node, which attaches the given agent to itself.Therefore, a user can do the same
thing by,
for example, $n0 attach $tcp.Similarly, each agent object has a member
function.
$ns connect agent1 agent2: After two agents that will communicate with each other are created,
the next thing is to establish a logical network connection between them.This line establishes
a network connection by setting the destination address to each others ' network and port
address pair.
Assuming that all the network configuration is done, the next thing to do is write a simulation
scenario(i.e.simulation scheduling).The Simulator object has many scheduling member
functions.However, the one that is mostly used is the following: 
$ns at time "string": This member
function of a Simulator object makes the scheduler
(scheduler_ is the variable that points the scheduler object created by[new Scheduler] command at the beginning of the script) to schedule the execution of the specified string at
given simulation time.For example, $ns at 0.1 "$cbr start"
will make the scheduler call a start
32
member
function of the CBR traffic source object, which starts the CBR to transmit data.In
NS, usually a traffic source does not transmit actual data, but it notifies the underlying agent
that it has some amount of data to transmit, and the agent, just knowing how much of the
data to transfer, creates packets and sends them.
After all network configuration, scheduling and post - simulation procedure specifications are
done, the only thing left is to run the simulation.This is done by $ns run.