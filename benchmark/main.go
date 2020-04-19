package main

import (
	"context"
	"fmt"
	"io/ioutil"
	"net/http"
	"strings"
	"time"

	"benchmark-client/pkg"

	"github.com/thinhdanggroup/executor"

	"google.golang.org/grpc"
)

func main() {

	executor, err := executor.New(executor.DefaultConfig())
	checkErr(err)

	noReq := int64(100000)
	callGrpc(executor, "localhost:6790", noReq)
	time.Sleep(2 * time.Second)
	callHttp(executor, "http://localhost:6789", noReq)
}

func callGrpc(executor *executor.Executor, target string, noReq int64) {
	// Prepare
	request := &pkg.PingRequest{}

	conn, err := grpc.Dial(target, grpc.WithInsecure())
	checkErr(err)
	defer conn.Close()

	server := pkg.NewCoreServiceClient(conn)

	// init connection
	_, err = server.Ping(context.Background(), request)
	checkErr(err)

	// Run test
	start := makeTimestamp()

	for i := int64(0); i < noReq; i++ {
		executor.Publish(
			func() {
				_, err := server.Ping(context.Background(), request)

				if err != nil {
					fmt.Errorf("Error request: %s", err)
				}
			})
	}

	executor.Wait()

	execTime := (makeTimestamp() - start)
	report("gRPC", noReq, execTime)
}

func callHttp(executor *executor.Executor, target string, noReq int64) {
	// Prepare
	url := target + "/api/ping"
	method := "POST"

	// Run
	start := makeTimestamp()

	for i := int64(0); i < noReq; i++ {
		executor.Publish(postRequest, method, url)
	}

	executor.Wait()

	execTime := (makeTimestamp() - start)
	report("HTTP", noReq, execTime)
}

func postRequest(method string, url string) {
	payload := strings.NewReader("{\n  \"timestamp\": 20\n}")

	client := &http.Client{}
	req, err := http.NewRequest(method, url, payload)
	checkErr(err)
	req.Header.Add("Content-Type", "application/json")

	res, err := client.Do(req)
	checkErr(err)

	defer res.Body.Close()
	_, err = ioutil.ReadAll(res.Body)

	if err != nil {
		fmt.Errorf("Error request: %s", err)
	}
	// fmt.Println(string(body))

}

func report(testName string, total int64, execTime int64) {
	fmt.Println("======================================")
	fmt.Println("TestName: " + testName)
	fmt.Println("Benchmark result for ", total, " request in ", execTime, " Microseconds")
	fmt.Println("Throughtput :", total*1000000/execTime, "req/s")
	fmt.Println("Latency :", execTime/total, "Microseconds")
	fmt.Println("======================================")
}

func makeTimestamp() int64 {
	return time.Now().UnixNano() / 1000
}

func checkErr(err error) {
	if err != nil {
		panic(err)
	}
}
