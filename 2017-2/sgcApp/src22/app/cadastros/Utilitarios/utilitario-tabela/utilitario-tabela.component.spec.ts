import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UtilitarioTabelaComponent } from './utilitario-tabela.component';

describe('UtilitarioTabelaComponent', () => {
  let component: UtilitarioTabelaComponent;
  let fixture: ComponentFixture<UtilitarioTabelaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UtilitarioTabelaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UtilitarioTabelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
