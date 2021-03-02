import { Component, OnInit } from '@angular/core';
import { ImageLoaderService } from '../image-loader.service';

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.css']
})
export class ImageComponent implements OnInit {


  
  constructor(private service: ImageLoaderService) { }


  ngOnInit(): void {
    this.service.getImage("SMITE").subscribe();
  }



}
