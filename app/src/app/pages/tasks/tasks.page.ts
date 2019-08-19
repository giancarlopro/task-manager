import { Component, OnInit } from '@angular/core';
import { Task } from 'src/app/interfaces/task';
import { AlertController } from '@ionic/angular';
import { TaskManagerApiService } from 'src/app/services/task-manager-api.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.page.html',
  styleUrls: ['./tasks.page.scss'],
})
export class TasksPage implements OnInit {
  tasks: Task[] = []

  constructor(
    private alertController: AlertController,
    private taskManagerApiService: TaskManagerApiService
  ) {
    console.log(this.taskManagerApiService.getAllTasks())
  }

  ngOnInit() {
    this.taskManagerApiService.getAllTasks().subscribe(data => this.tasks = data)
  }

  refreshTasks(event) {
    setTimeout(_ => {
      this.taskManagerApiService.getAllTasks().subscribe(data => this.tasks = data)
      event.target.complete()
    }, 1000)
  }

  removeTask(id) {
    this.taskManagerApiService.finishTask(id).subscribe(_ => {
      this.tasks = this.tasks.filter((task) => task.id != id)
    })
  }

  async addTaskAlert() {
    let alerta = await this.alertController.create({
      header: "Nova Tarefa",
      buttons: [
        "Cancel",
        {
          text: "OK",
          handler: data => {
            if (!(data.nome == '' || (data.nome == '' && data.descricao == ''))) {
              this.taskManagerApiService.createTask(data.nome, data.descricao).subscribe(
                task => this.tasks.push(task)
              )
            }
          }
        }
      ],
      inputs: [
        {
          type: "text",
          name: "nome",
          placeholder: "Nome:"
        }, {
          type: "text",
          name: "descricao",
          label: "Descrição:",
          placeholder: "Descrição"
        }
      ]
    })
    await alerta.present()
  }
}
