import grpc

import grpc_proto.Mekur_pb2_grpc as pb2_grpc
import grpc_proto.Mekur_pb2 as pb2


def run():
    with grpc.insecure_channel('localhost:9090') as channel:
        stub = pb2_grpc.StudentServiceStub(channel)
        response = stub.getStudentById(pb2.StudentRequest(studentId='223'))
    print("Greeter client received following from server: " + response.dept)


run()
