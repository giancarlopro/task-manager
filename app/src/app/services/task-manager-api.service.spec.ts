import { TestBed } from '@angular/core/testing';

import { TaskManagerApiService } from './task-manager-api.service';

describe('TaskManagerApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TaskManagerApiService = TestBed.get(TaskManagerApiService);
    expect(service).toBeTruthy();
  });
});
