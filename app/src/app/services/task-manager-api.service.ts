import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Task } from '../interfaces/task';

@Injectable({
  providedIn: 'root'
})
export class TaskManagerApiService {
  base_url: string = 'http://localhost:8080/api'
  // base_url: string = 'http://dummy.restapiexample.com/api/v1/employees'
  
  constructor(private httpClient: HttpClient) { }

  public getAllTasks() {
    return this.httpClient.get<Task[]>(`${this.base_url}/tasks`)
  }

  public finishTask(id: any) {
    return this.httpClient.get(`${this.base_url}/tasks/${id}/done`)
  }

  public createTask(nome: String, descricao: String) {
    return this.httpClient.post<Task>(
      `${this.base_url}/tasks`,
      {
        "nome": nome,
        "descricao": descricao
      },
      {
        headers: {"Content-Type": "application/json"}
      }
    )
  }
}
