import grpc
import grpc_proto.Mekur_pb2_grpc as pb2_grpc
import grpc_proto.Mekur_pb2 as pb2

import time
from concurrent import futures


class Greeter(pb2_grpc.StudentServiceServicer):
    def getStudentById(self, request, context):
        print("Got request " + str(request))
        return pb2.StudentResponse(studentId="123", name="abid", semester=3, dept="cse", cgpa=2.5)


def start_server():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=2))
    pb2_grpc.add_StudentServiceServicer_to_server(Greeter(), server)
    server.add_insecure_port('[::]:50051')
    print("gRPC starting")
    server.start()
    server.wait_for_termination()


start_server()
